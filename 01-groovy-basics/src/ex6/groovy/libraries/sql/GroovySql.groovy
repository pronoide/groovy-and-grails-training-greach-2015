package ex6.groovy.libraries.sql

import groovy.sql.Sql
def select = 'select * from table'

def q = Sql.newInstance('jdbc:...',
		'user', 'pwd', 'driver')

q.eachRow(select) {  println "$it.id --${it.column}--" }
row = q.firstRow(select)
println "${row.column}"

List rows = q.rows(select)

def sql = 'insert into table(column) values(?)'
def params = ["Groovy"]

if(q.execute(sql, params))
	q.commit()
else
	q.rollback()

q.close()

