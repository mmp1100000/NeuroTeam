from mysql_connector import Connection

connection = Connection()
connection.make_query("SELECT * FROM Paciente;")
connection.close()