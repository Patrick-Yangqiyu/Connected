## Connected Java

### Description

#### Connected
main class 


#### City
object class

#### CityTest
addLink(String startCity, String endCity)

call addCoonCities

addCoonCities(String cityA, String cityB)


recursion()

set all connect city isConnected parameter to ture

isConnected()

return boolean 

### How to use
```
-javac Connected.java

-java Connected filename.txt city1 city2
```

### Output

```
Windows PowerShell
Copyright (C) Microsoft Corporation. All rights reserved.

PS D:\Code\Fenics> java Connected cities.txt Boston Tampa
**** Boston is connected to Tampa
yes
PS D:\Code\Fenics> java Connected cities.txt "New York" Tampa
**** New York is connected to Tampa
yes
PS D:\Code\Fenics> java Connected cities.txt Boston T
**** Boston or T is not in the file
no
PS D:\Code\Fenics> java Connected cities.txt Petersburg Boston
**** Petersburg or Boston is not in the file
no
```