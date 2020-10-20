import xml.etree.ElementTree as ET
import sys
import traceback
import readline

# Docs: https://docs.python.org/3/library/xml.etree.elementtree.html


def main():
	USAGE = f"Usage: python {sys.argv[0]} xmlPath"
	if (len(sys.argv) != 2):
		raise SystemExit(USAGE)
	path = sys.argv[1]
	# Load xml
	try:
		load(path)
	except:
		print(f"Cannot open file {path}.")
		return
	# Loop for user input
	while(1):
		val = input("Enter command: ")
		c = val.split(' ')
		mainC = c[0]
		# Note: don't put the quit function inside a try except because it raises an exception to exit the program
		if mainC == "quit" or mainC == "q":
			save(path)
			quit()
		elif mainC == "quitNoSave" or mainC == "qws":
			quit()
		else:
			try:
				if mainC == "viewNode" or mainC == "vn":
					viewNode(c[1:])
				elif mainC == "viewGraph" or mainC == "vg":
					viewGraph(c[1:])
				elif mainC == "addNode" or mainC == "an":
					if len(c[1:]) == 2 and isInt(c[1]):
						addNode(c[1], c[2])
					else:
						print("Invalid command.")
				elif mainC == "removeNode" or mainC == "rn":
					if len(c[1:]) == 1 and isInt(c[1]):
						removeNode(c[1])
					else:
						print("Invalid command.")
				elif mainC == "addEdge" or mainC == "ae":
					# Use: ae weight, left_id, right_id, left_angle, right_angle, 
					if len(c[1:]) >= 6 and isFloat(c[1]) and all(map(isInt, c[2:6])):
						addEdge(c[1], c[2], c[3], c[4], c[5], c[6:])
					else:
						print("Invalid command.")
				elif mainC == "removeEdge" or mainC == "re":
					if len(c[1:]) == 2:
						removeEdge(c[1], c[2])
					else:
						print("Invalid command.")
				else:
					print(f"Command {c[0]} does not exist.")
			except:
				print("An exception occurred: ")
				traceback.print_exc()

# Program functions
def load(path):
	global tree
	global root
	tree = ET.parse(path)
	root = tree.getroot()

def save(path):
	tree.write(path)

def quit():
	sys.exit()

# General functions
def isInt(s):
	try:
		foo = int(s)
		return True
	except:
		return False

def isFloat(s):
	try:
		foo = float(s)
		return True
	except:
		return False

# XML functions
def addSubElements(element, subElements):
	for e in subElements:
		ET.SubElement(element, e)

def addTextElements(element, textElements):
	for tag, value in textElements.items():
		ET.SubElement(element, tag).text = value

def addTextArray(element, name, texts):
	for text in texts:
		ET.SubElement(element, name).text = text

# Node functions
def viewNode(a):
	if len(a) == 0:
		for node in root.find('nodes').findall('node'):
			print(f"Id: {node.find('id').text} Name: {node.find('name').text}")
	elif len(a) == 1:
		idNum = a[0]
		node = findNode(idNum)
		if node != None:
			print(f"Id: {node.find('id').text} Name: {node.find('name').text}")
		else:
			print(f"Node does not exist with id {idNum}")
	else:
		print("Invalid command.")

def findNode(idNum):
	for node in root.find('nodes').findall('node'):
		if node.find('id').text == idNum:
			return node
	return None

def addNode(idNum, name):
	if findNode(idNum) != None:
		print(f"Node already exists with id {idNum}.")
		return
	e = ET.Element('node')
	ET.SubElement(e, 'id').text = idNum
	ET.SubElement(e, 'name').text = name
	root.find('nodes').append(e)

def removeNode(idNum):
	node = findNode(idNum)
	if not node is None:
		root.find('nodes').remove(node)
		for edge in root.find('edges').findall('edge'):
			if edge.find('left').find('id').text == idNum or edge.find('right').find('id').text == idNum:
				removeEdge(edge.find('left').find('id').text, edge.find('right').find('id').text)
	else:
		print(f"Node with id {idNum} does not exist.")

# Edge functions
def viewGraph(a):
	if len(a) == 0:
		for edge in root.find('edges').findall('edge'):
			print(f"{edge.find('left').find('id').text} connects {edge.find('right').find('id').text} Rooms:", end=' ')
			for room in edge.find('rooms').findall('room'):
				print(room.text, end=' ')
			print()
	elif len(a) == 1:
		idNum = a[0]
		for edge in root.find('edges').findall('edge'):
			if edge.find('left').find('id').text == idNum or edge.find('right').find('id').text == idNum:
				print(f"{edge.find('left').find('id').text} connects {edge.find('right').find('id').text}")
	elif len(a) == 2:
		id1 = a[0]
		id2 = a[1]
		for edge in root.find('edges').findall('edge'):
			if (edge.find('left').find('id').text == id1 and edge.find('right').find('id').text == id2) or (edge.find('left').find('id').text == id2 and edge.find('right').find('id').text == id1):
				print(
					f"Weight: {edge.find('weight').text}\n"
					f"Left: {edge.find('left').find('id').text} at {edge.find('left').find('angle').text} degrees. "
					f"Right: {edge.find('right').find('id').text} at {edge.find('right').find('angle').text} degrees.\n"
					f"Rooms:", end=' '
				)
				for room in edge.find('rooms').findall('room'):
					print(room.text, end=' ')
				print()
	else:
		print("Invalid command.")

def findEdge(idNumL, idNumR):
	for edge in root.find('edges').findall('edge'):
		idL = edge.find('left').find('id').text
		idR = edge.find('right').find('id').text
		if (idNumL == idL or idNumL == idR) and (idNumR == idL or idNumR == idR):
			return edge
	return None

def addEdge(weight, idNumL, idNumR, angleL, angleR, rooms):
	if findEdge(idNumL, idNumR) != None:
		print(f"Nodes {idNumL} and {idNumR} already connected.")
		return
	edge = ET.Element('edge')
	addSubElements(edge, ['weight', 'left', 'right', 'rooms'])
	edge.find('weight').text = weight
	addTextElements(edge.find('left'), {'angle': angleL, 'id': idNumL})
	addTextElements(edge.find('right'), {'angle': angleR, 'id': idNumR})
	addTextArray(edge.find('rooms'), 'room', rooms)
	root.find('edges').append(edge)

def removeEdge(idNumL, idNumR):
	found = False
	for edge in root.find('edges').findall('edge'):
		if edge.find('left').find('id').text == idNumL and edge.find('right').find('id').text == idNumR:
			root.find('edges').remove(edge)
			found = True
	if found == False:
		print(f"Cannot find edge that connects {idNumL} and {idNumR}.")

if __name__ == "__main__":
	main()