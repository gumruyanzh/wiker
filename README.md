"# wiker" 
Simple Spring Boot app which lets you download and save the title and bio image of provided wikipedia URL 

"#run app"
1. mvn -DskipTests=true package
2. cd target
3. java -jar wiker-0.0.1-SNAPSHOT.jar https://en.wikipedia.org/wiki/Henry_Ford

P.S. if you don't provide any URL, by default it will scrap youtube wiki page data ;)