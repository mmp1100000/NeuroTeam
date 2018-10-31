from mysql_connector import Connection

connection = Connection()
pacientes = connection.make_query("SELECT * FROM Paciente;")

for e in pacientes:
    for i in e:
        print(i)

connection.close()
