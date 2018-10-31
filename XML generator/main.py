from mysql_to_xml import MysqlToXml
from mysql_connector import Connection
import mysql.connector

test = MysqlToXml(Connection(mysql.connector.connect(
        host="127.0.0.1",
        user="root",
        passwd="qwerty",
        database="neuroteam",
        port=3306)),
        database_name="neuroteam")
