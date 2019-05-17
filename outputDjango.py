class Professor(Person):
	orientedStudents = models.ForeignKey('Studant', on_delete=models.CASCADE)
	id = models.IntegerField(primary_key=True)

class GraduatedStudant(Studant):
	id = models.IntegerField(primary_key=True)

class Person(models.Model):
	name = ()
	id = models.IntegerField(primary_key=True)

class Studant(Person):
	id = models.IntegerField(primary_key=True)

