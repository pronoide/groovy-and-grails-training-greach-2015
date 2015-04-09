package ex10.grails.sesame

import grails.test.spock.IntegrationSpec

class MuppetIntegrationSpec extends IntegrationSpec {

	def "test save several muppets"() {
		when:
			def muppet = new Muppet([name: name, color: color, gender: gender]).save()
		then:
			muppet!=null == result
		where:
			name   | gender   		  | color			  | result
			'ernie'| Muppet.MALE    | 'orange' 		| true
			'bert' | Muppet.MALE    | null		    | false
			'elmo' | Muppet.MALE    | 'red'		    | true
			'zoe'  | Muppet.FEMALE  | 'orange'	  | true
	  }
}

