from mysql_connector import Connection
from xml.etree.ElementTree import Element, SubElement, Comment, tostring


class MysqlToXml:
    def __init__(self, connection, database_name):
        self.connection = connection
        root = Element(database_name)
        for table in self.get_tables():
            self.parse_table(table, root)

        print(tostring(root))

    def get_tables(self):
        query = self.connection.make_query("SHOW TABLES;")
        tables = list()
        for row in query:
            tables.append(row[0])
        return tables

    def parse_table(self, table_name, root):
        query = self.connection.make_query("SELECT * FROM " + table_name + ";")
        table_columns = query.column_names
        for row in query:
            table_root = SubElement(root, table_name, {table_columns[0]: str(row[0])})
            for i in list(range(1, len(table_columns))):
                SubElement(table_root, table_columns[i]).text = str(row[i])

        return root
