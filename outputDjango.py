class Professor(Person):
	orientedStudents = models.ForeignKey('Studant', on_delete=models.CASCADE)
	id = models.IntegerField(primary_key=True)

	class Meta:
		db_table = 'table_person'


class GraduatedStudant(Studant):
	id = models.IntegerField(primary_key=True)

	class Meta:
		db_table = 'table_person'


class Person(models.Model):
	name = models.CharField()
	id = models.IntegerField(primary_key=True)

	class Meta:
		db_table = 'table_person'


class Studant(Person):
	id = models.IntegerField(primary_key=True)

	class Meta:
		db_table = 'table_person'


