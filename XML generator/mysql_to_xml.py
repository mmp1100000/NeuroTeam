from xml.dom import minidom
from xml.etree.ElementTree import Element, SubElement, tostring


class MysqlToXml:
    def __init__(self, connection, database_name, path):
        self.connection = connection
        root = Element(database_name)
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
        if not (table_name == "Medico_has_Paciente"):
            for row in query:
                table_root = SubElement(root, table_name, {table_columns[0]: str(row[0])})
                for i in list(range(1, len(table_columns))):
                    SubElement(table_root, table_columns[i]).text = str(row[i])
        else:

            for row in query:
                attr_dict = dict()
                for i in list(range(0, len(table_columns))):
                    attr_dict[table_columns[i]] = str(row[i])
                    print(table_columns[i])
                SubElement(root, table_name, attr_dict)

        return root

    def check_all_pk(self, table_name):  # @TODO
        get_pk = self.connection.make_query("show index from " + table_name + "where Key_name = 'PRIMARY';")
        print(get_pk)
        get_ncol = self.connection.make_query(
            "SELECT count(*) FROM information_schema.columns WHERE table_name = '" + table_name + "'")
        if len(get_pk) == int(get_ncol[0]):
            return True
        else:
            return False

    @staticmethod
    def to_file(path, tree):
        xmlstr = minidom.parseString(tree).toprettyxml(indent="   ")
        with open(path, "w") as f:
            f.write(xmlstr)
