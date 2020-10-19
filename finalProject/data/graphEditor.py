import xml.etree.ElementTree as ET
import sys

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
		if mainC == "quit" or mainC == "q":
			save(path)
			quit()
		elif mainC == "viewNodes" or mainC == "vn":
			viewNodes()
		elif mainC == "viewGraph" or mainC == "vg":
			

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
def viewNodes():
	for node in root.find('nodes').findall('node'):
		print(f"Id: {node.find('id').text} Name: {node.find('name').text}")

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
	if node != None:
		root.find('nodes').remove(node)
	else:
		print(f"Node with id {idNum} does not exist.")

# Edge functions
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
	root.find('edges').append(e)


if __name__ == "__main__":
	main()