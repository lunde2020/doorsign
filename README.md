# doorsign

F&E Projekt WS2015/2016 - Elektrisches Türschild

Neuen Programmstatus hochladen:
===============================

$ git add --all           						# adds everything in current path
$ git commit -m "Kommentar"						# Kommentar für Programmstatus
$ git push										# lädt neuen Status hoch

alternativ  für den add Befehl:

$ git add .               						# adds everything in current path
$ git add [file]          						# adds only the specific file 


Branch --> Pull Request --> Merge
=================================

git checkout -b [name_of_your_new_branch]   	# Starte neues Branch und wechsle rein
git checkout [name_of_your_new_branch]			# wechselt nur rein
$ git push origin [name_of_your_new_branch]		# hochladen von Branch
$ git branch									# welche Branchs gibts

Delete a branch on your local filesystem :

$ git branch -d [name_of_your_new_branch]   	# Delete a branch on your local filesystem :

$ git branch -D [name_of_your_new_branch]		#force the deletion of local branch on your filesystem :

$ git push origin :[name_of_your_new_branch]	#Delete the branch on github :