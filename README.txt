#Entry point:
- http://localhost:8080/api/search

#Accepted search parameters:
 - orderByName (ASC,DESC) default ASC
 - orderByHeight (ASC,DESC) default ASC
 - limit
 - category (MUN, BOTH, TOP) default BOTH
 - minHeight
 - maxHeight

Arguments can be chained example:
http://localhost:8080/api/search?orderByHeight=asc&limit=3&minHeight=950

Depending on the order of passed parameters, result will be different
As per document the Munroes with empty category for post1997 are being stripped out from all result set, doesn't matter if category is passed

#Build
gradlew clean test assemble
java -jar build/libs/munro-library-0.0.1-SNAPSHOT.jar