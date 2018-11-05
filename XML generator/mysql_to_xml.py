from xml.dom import minidom
from xml.etree.ElementTree import Element, SubElement, tostring


# @ TODO possible improvement: http://code.activestate.com/recipes/578503-validate-xml-with-schemalocation/


class MysqlToXml:
    def __init__(self, connection, database_name, path, xsi_location):
        self.connection = connection
        root = Element(database_name,
                       {"xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
                        "xsi:schemaLocation": xsi_location})
        for table in self.get_tables():
            self.parse_table(table, root)

        self.to_file(tree=tostring(root), path=path)

    def get_tables(self):
        query = self.connection.make_query("SHOW TABLES;")
        tables = list()
        for row in query:
            tables.append(row[0])
        return tables

    def parse_table(self, table_name, root):
        query = self.connection.make_query("SELECT * FROM " + table_name + ";")
        table_columns = query.column_names
        if not (table_name == "medico_has_paciente"):
            for row in query:
                table_root = SubElement(root, table_name, {table_columns[0]: str(row[0])})
                for i in list(range(1, len(table_columns))):
                    SubElement(table_root, table_columns[i]).text = str(row[i])
        else:
            for row in query:
                attr_dict = dict()
                for i in list(range(0, len(table_columns))):
                    attr_dict[table_columns[i]] = str(row[i])

                SubElement(root, table_name, attr_dict)

        return root

    def check_all_pk(self, table_name):  # @ TODO fix this function in order to replace for Medico_has_Paciente
        get_pk = self.connection.make_query("show index from " + table_name + "where Key_name = 'PRIMARY';")

        get_ncol = self.connection.make_query(
            "SELECT count(*) FROM information_schema.columns WHERE table_name = '" + table_name + "'")
        if len(get_pk) == int(get_ncol[0]):
            return True
        else:
            return False

    @staticmethod
    def to_file(path, tree):
        xmlstr = minidom.parseString(tree).toprettyxml(indent="   ")
        first_line = xmlstr[0:xmlstr.find("\n")] + "\n"
        stylesheet_line = "<?xml-stylesheet type=\"text/xsl\" href=\"XMLSchema/"+ path +".xsl\"?>\n"
        xml_content = xmlstr[xmlstr.find("\n")+1:]
        xml_total = first_line + stylesheet_line + xml_content
        with open(path+".xml", "w") as f:
            f.write(xml_total)


