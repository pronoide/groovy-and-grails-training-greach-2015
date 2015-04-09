package ex10.grails.sesame

class Muppet {

	final static Character MALE = 'M'
	final static Character FEMALE = 'F'
	final static Character ANY = '%'

	String name
	String about
	String color
	Character gender
	boolean hasFur = Boolean.FALSE
	String picture
	static hasMany = [clips: String]

	static constraints = {
		name nullable: true, size: 2..15
		about nullable: true, size: 0..1000
		color blank: false
		gender nullable: true
		picture nullable: true 
		clips nullable: true
	}

	static mapping = {
		table 'muppets', 
			id_column: 'muppet_id',
			about: [length: 1000]
		clips joinTable: 
			[name: 'muppets_videoclips', key: 'muppet_id', column: 'clip_url', type: "text"]
	}
}
