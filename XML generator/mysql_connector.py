import mysql.connector


# IMPORTANTE: INSTALAR mysql-connector-python. No otro.

def connect():
    return mysql.connector.connect(
        host="127.0.0.1",
        user="root",
        passwd="qwerty",
        database="neuroteam",
        port=3306)


class Connection:
    def __init__(self):
        self.db_connection = connect()
        self.db_connection_cursor = self.db_connection.cursor()

    def make_query(self, query):
        self.db_connection_cursor.execute(query)
        for res in self.db_connection_cursor:
            print(res)

    def close(self):
        self.db_connection.close()
        self.db_connection_cursor.close()