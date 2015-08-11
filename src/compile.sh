cmd1="javac -cp sqljet-1.1.10.jar:javax.json-api-1.0.jar:simplenlg-v4.4.2.jar:. SentenceGenerator.java"
echo $cmd1
$cmd1

cmd2="javac -cp sqljet-1.1.10.jar:javax.json-api-1.0.jar:simplenlg-v4.4.2.jar:. Translator.java SentenceGenerator.java Language.java"
echo $cmd2
$cmd2