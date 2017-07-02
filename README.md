# Tilde
----
Tilde is a functional tool-belt for Android. It not just only provides functional methods for Kotlin but also some wrapper for Java so you can play around with the library in Java too.
Tilde is inspired from [Dollar][dollar] in Swift which is similar to [Lodash][lodash] and [Underscore.js][underscore-js] in Javascript.

> **NOTE: This library is under development**
# Quick Jump
---
  - [Setup](#setup)
  - [Usage](#usage)
  - [Contributing](#contributing)
  - [License](#license)

# Setup
---
## Gradle Setup
In your **build.gradle** file, add the following dependency
```groovy 
compile 'com.tilde:tilde:1.0.0'
```
## Maven Setup
Add the following dependency
```xml
<dependency>
    <groupId>com.tilde</groupId>
    <artifactId>tilde</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
</dependency>
```

# Usage
----
# Assign - `T?.assign`
Consider a map where we have mapped characters to list of their occurences. e.g. in kotlin we have 
```kotlin
val map = `mapOf('a' to arrayListOf("Apple", "Avocado"), 'm' to arrayListOf("Mango"), 't' to arrayListOf("Tomato"))`
```
And we have a new value `Banana`. Our code would be like
```kotlin
val fruit = "Banana"
val key = fruit.elementAt(0) // key is b here
var occurences = map.get(key)
if (occurences == null) {
    occurences = ArrayList<String>()
    map.put(key, occurences)
}
occurences.add(fruit)
```
But **Tilde** makes our life simple
```kotlin
val fruit = "Banana"
val key = fruit.elementAt(0) // key is b here
val occurences = map.get(key).assign {
    // The block will only execute if map.get(key) returns null
    val list =  ArrayList<String>()
    map.put(key, list)
    return@assign list
}
occurences.add(fruit)
```
# At - `Iterable.at`
Consider we have a list 
```kotlin
val list = arrayListOf("User first", "User second", "User third", "User fourth", "User fifth")
```
And we want elements at 0th, 4th and 3rd index. We can get it simply by 
```kotlin
val selected = list.at(0, 4, 3)
// Selected here will be arrayListOf("User first", "User fifth", "User fourth")
```
# Chunks - `Iterable.chunks`
Consider we have list of students roll number
```kotlin
val list = arrayListOf("1001", "1002", "1003", "1004", "1005", "1006", "1007", "1008", "1009", "1010", "1011", "1012", "1013", "1014")
```
And we want to create groups in sequence having maximum of 3 students. We can acheive this by the following code
```kotlin
val groups = list.chunks(3)
```
And here `groups` will have a 2D array as follows
```
1001    1002    1003
1004    1005    1006
1007    1008    1009
1010    1011    1012
1013    1014
```


# Contributing
----
Feel free to add new methods and submit a pull request. 
 - Code
 - Add comments to your code (if necessary)
 - Add documentation to your methods
 - Not to forget adding test case for your methods
 - Add function usage in README.md
 - Generate pull request
> No **Pull Request** will be entertained without test cases

# License
----

    MIT License
    
    Copyright (c) 2017 Shahroz Khan
    
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


[dollar]: <https://github.com/ankurp/Dollar>
[lodash]: <https://lodash.com/>
[underscore-js]: <http://underscorejs.org/>
