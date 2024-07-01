@echo off

rem Recuperation du nom du projet
set nameProject=CinemaNaka

rem Chemin vers repertoire temporaire
set temp=temp
set tempsrc=tempsrc

rem Verifie si le dossier temp existe dejà
if exist "%temp%" (
    rd /S /Q "%temp%"
    echo Le dossier %temp% et son contenu ont ete supprimes avec succes.
)

rem Verifie si le dossier tempsrc existe dejà
if exist "%tempsrc%" (
    rd /S /Q "%tempsrc%"
    echo Le dossier %tempsrc% et son contenu ont ete supprimes avec succes.
)

mkdir "%temp%"
echo Le nouveau dossier %temp% a ete cree avec succes.

mkdir "%tempsrc%"
echo Le nouveau dossier %tempsrc% a ete cree avec succes.

rem Definition des dossiers necessaires
set librairie=lib
set xml=web.xml
set src=src
set view=views

rem Definition des destinations pour chaque dossier
set deslibrairie=%temp%\WEB-INF\lib\
set desxml=%temp%\WEB-INF\
set desview=%temp%\view\

rem Creation des dossiers de destination
mkdir "%deslibrairie%"
mkdir "%desxml%"
mkdir "%desview%"
mkdir "%temp%\WEB-INF\classes\"

rem Copie du dossier librairie vers le dossier de destination
xcopy "%librairie%" "%deslibrairie%" /E /I /Y
echo La copie du dossier %librairie% a ete effectuee avec succes dans %deslibrairie%.

rem Copie du dossier view vers le dossier de destination
xcopy "%view%" "%desview%" /E /I /Y
echo La copie du dossier %view% a ete effectuee avec succes dans %desview%.

rem Copie des fichiers .xml vers le dossier de destination
copy "%xml%" "%desxml%"
echo Le fichier %xml% a ete copie dans %desxml%.

rem Copie des sources dans tempsrc
xcopy "%src%" "%tempsrc%" /E /I /Y
echo La copie des fichiers sources depuis %src% vers %tempsrc% a ete effectuee avec succes.

rem Compilation de tous les fichiers Java du repertoire tempsrc
javac -cp "%deslibrairie%*" -d "%temp%\WEB-INF\classes" "%tempsrc%\model\*.java" "%tempsrc%\controller\*.java"

echo Compilation des fichiers Java dans %tempsrc% terminee. Les fichiers .class sont stockes dans %temp%\WEB-INF\classes\.

rem Deplacement des fichiers compiles vers le dossier webapps de Tomcat
xcopy "%temp%" "C:\Program Files\Apache Software Foundation\TomcatNaka\webapps\%nameProject%" /E /I /Y
echo Deploiement des fichiers vers Tomcat dans le dossier %nameProject% effectue avec succes.

echo Deploiement complet.

pause