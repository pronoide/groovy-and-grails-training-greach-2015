package ex6.groovy.libraries.builders;

import groovy.swing.SwingBuilder
import java.awt.FlowLayout

builder = new SwingBuilder()
frame = builder.frame(
		title:'Update balance',
		size:[200, 150]) {

			panel(layout: new FlowLayout()) {
				label(text:"enter balance")
				textField(text:"500",
				preferredSize:[100, 20],
				horizontalAlignment:0)
				button(text:"Update",
				actionPerformed: { update() })
			}
		}
def update() {
	pane = builder.optionPane(
			message:'Data received')
	dialog = pane.createDialog(
			frame, 'Success')
	dialog.show()
}
frame.setVisible true
