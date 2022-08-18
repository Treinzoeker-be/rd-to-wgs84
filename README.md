# RD-to-WGS84 Java library

This tiny Java library consists of a single class with utility methods to convert
Dutch "Rijksdriehoeksstelsel" (RD) coordinates to WGS84 latitude/longitude and back.

These methods were primarily created to be used to convert Dutch station
coordinates, exchanged in RD-coordinates, to WGS84 values. Given that the
dataset providing the stations also uses RD-coordinates for foreign stations
(in Germany, France, Italy, ...), the methods in this library **DO NOT** check
for illegal input. Input (far) outside the range _will be_ converted back and forth,
but might lose precision.

### Usage
We use Maven as dependency manager, and publish artifacts to a publicly available repository.

```xml
<repositories>
    <repository>
        <id>treinzoeker-be</id>
        <url>https://repo.treinzoeker.be/repo/</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
        <groupId>com.ovtijden</groupId>
        <artifactId>rd-to-wgs84</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
