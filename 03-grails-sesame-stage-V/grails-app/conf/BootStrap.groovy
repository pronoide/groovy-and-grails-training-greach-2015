import ex10.grails.sesame.Muppet
import grails.util.Environment

class BootStrap {

	def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:

				def muppets=[
					new Muppet(name:'Ernie', gender: Muppet.MALE, color: "orange", hasFur: false, picture: 'ernie.jpg',
					about:'''Some people call me wise, playful and naturally outgoing. I like to have fun, especially with my best friend Bert. I like to ask a lot of questions and I am great at explaining things, but can sometimes be a bit too smart for my own good.''',
					clips: ['Mh85R-S-dh8', 'pigcahytIH8']),
					new Muppet(name:'Bert', gender: Muppet.MALE, color: "yellow", hasFur: false, picture: 'bert.jpg',
					about:'''I am the long-suffering sidekick of Ernie. I am more mature, analytical, and the voice of reason in our relationship. Some might view me as eccentric because I collect bottle caps and paper clips, play the tuba, and love Bernice, my pet pigeon. I'm not always a willing participant in Ernie's escapades because I always sense that the tables are destined to be turned on me, or that I will end up with the short end of the stick! In the end, however, I always forgive Ernie, forever remaining his "old buddy."''',
					clips: ['-oD1xuAJMak', 'pigcahytIH8']),
					new Muppet(name:'Elmo', gender: Muppet.MALE, color: "red", hasFur: true, picture: 'elmo.jpg',
					about:'''Elmo is 3 ' years old. Elmo has red fur and Elmo loves playing with his friends Zoe and Abby. Elmo loves riding on this tricycle and being tickled. Elmo also has a pet goldfish, Dorothy. Elmo's friends tell Elmo that Elmo has a contagious giggle. Elmo likes to be enthusiastic, friendly, and cheerful, and Elmo always wants to be part of everything that goes on. Sometimes Elmo doesn't know how to do what Elmo wants. But that never stops Elmo because Elmo has a very positive, optimistic view of Elmo and life!''',
					clips: ['vSYadh2xmcI', 't0WELUxl7gc']),
					new Muppet(name:'Cookie Monster', gender: Muppet.MALE, color: "blue", hasFur: true, picture: 'cookie.jpg',
					about:'''All this talk of cookie make me hungry! Me a frenzied, but cuddly, monster. Me eat everything; chairs, microphones, numbers, letters, vegetables; me always on eternal quest to find more food (especially cookies)! Me like to solve problems, like what to eat when I'm hungry!''',
					clips: [
						'Ye8mB6VsUHw',
						'kKW7TIjqZAA',
						'KBMxpDbp51A'
					]),
					new Muppet(name:'Zoe', gender: Muppet.FEMALE, color: "orange", hasFur: true, picture: 'zoe.jpg',
					about:'''I am known for talking at very high speeds and my beautiful ballet skills. I sometimes get so excited that my words cannot get out fast enough. I can also get jealous if I don't get enough attention. I like designing floating devices for my pet Rocco.''',
					clips: ['KkvCZLU1sGI']),
					new Muppet(name:'Grover', gender: Muppet.MALE, color: "blue", hasFur: true, picture: 'grover.jpg',
					about:'''I am a cute and furry blue monster. That is me. I am excitable, caring and compulsive'a combination that sometimes gets me into trouble. I love to help people but because of my inexperience, I usually end up doing things the long way around. If I do not succeed at first, I always try and try again. I have many jobs on Sesame Street but my favorite is working in Charlie's Restaurant.''',
					clips: ['auqyJ1FisSY', 'sIucCUyZF0g']),
					new Muppet(name:'Count Von Count', gender: Muppet.MALE, color: "purple", hasFur: true, picture: 'count.jpg',
					about:'''I am 1,832,652 years old! I've been counting. Some say I bear a comical resemblance to Count Dracula, but that is where the similarity ends. I thirst for numbers, not necks. I LOVE to count anything and everything! I'm very dependable; you can always count on me.''',
					clips: ['5l7KbMVdN7E', 'ZIniljT5lJI']),
					new Muppet(name:'Abby Cadabby', gender: Muppet.FEMALE, color: "purple", hasFur: true, picture: 'count.jpg',
					about:'''I am an inquisitive 3-year-old fairy-in-training who has moved to Sesame Street from Fairyside, Queens. My mommy is the Fairy Godmother. I am still learning magic and tend to turn things into pumpkins with my training wand. I go to Flying Fairy School with my friends Blogg and Gonnigan. I love fairytales, they are a part of my family history. I love bubbles and exploring them with my friends Elmo, Chris and Alan. I love to practice magic and rhyming, but what I find truly amazing and magical is what I discover on Sesame Street!''',
					clips: ['cjRQ6VyG1Yw']),
				]

				muppets.each{ it.save() }
		}
	}

	def destroy = {
	}
}