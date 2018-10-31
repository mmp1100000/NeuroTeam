import mysql.connector


# IMPORTANTE: INSTALAR mysql-connector-python. No otro.


class Connection:
    def __init__(self, db_connection):
        self.db_connection = db_connection
        self.db_connection_cursor = self.db_connection.cursor()

    def make_query(self, query):
        self.db_connection_cursor.execute(query)
        return self.db_connection_cursor

    def close(self):
        self.db_connection.close()
        self.db_connection_cursor.close()

    def get_db_name(self):
        return
