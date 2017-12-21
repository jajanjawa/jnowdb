### Apa ini ?
Pustaka untuk layanan backend dari [nowdb.net](http://nowdb.net)

[![](https://jitpack.io/v/jajanjawa/jnowdb.svg)](https://jitpack.io/#jajanjawa/jnowdb)

### Gradle
tambahkan pada `build.gradle`
```groovy
repositories { // pastikan bukan di dalam buildscript
	jcenter()
	mavenCentral()
	maven {
		url 'https://jitpack.io/'
	}
}
dependencies {
	compile 'com.github.jajanjawa:jnowdb:0.4'
	
	// atau
	//compile 'com.github.jajanjawa:jnowdb:-SNAPSHOT'
}
```

### Persiapan
```java
NowDBConfig config = new NowDBConfig()
			.token(TOKEN)
			.project(PROJECT)
			.appId(APP_ID);

NowDB nowDb = new NowDB.Builder().config(config).build();
```

### POST
#### java.util.Map
```java
Map<String, Object> data = new HashMap<>();
data.put("nama", "Kucing");
data.put("warna", "Putih");
data.put("timestamp", System.currentTimeMillis());

NowDBResponse response = nowDb.post("hewan", data).execute();
```
#### NowDBCollection
```java
public class Animal extends NowDBCollection {

	@SerializedName("nama")
	private String name;
	
	@SerializedName("warna")
	private String color;
	
	@SerializedName("timestamp")
	private Long timestamp;	

	@Override
	public String getCollectionName() {
		return "hewan";
	}
}

Animal animal = new Animal();
animal.setName("Kucing");
animal.setColor("Putih");
animal.timestamp(System.currentTimeMillis());

NowDBResponse response = nowDb.post(animal).execute();
Animal result = response.as(Animal.class);
String id = result.getId();
```
class `Animal` diatas menggunakan anotasi [Gson](https://github.com/google/gson) pada tiap field.

### PUT
#### java.util.Map
```java
Map<String, Object> data = new HashMap<>();
// isi data kamu

String id = "ID"; // didapat saat melakukan POST
NowDBResponse response = nowDb.put("hewan", id, data).execute();
Status status = response.status();
```
#### NowDBCollection
```java
Animal animal = new Animal();
// setel data kamu
animal.setId(ID); // didapat saat melakukan POST
NowDBResponse response = nowDb.put(animal).execute();
Status status = response.status();
```
#### NowDBQuery
```java
NowDBQuery query = new NowDBQuery("hewan")
				.equal("nama", "Kucing")
				.and()
				.equal("warna", "Putih");
					
Map<String, Object> data = new HashMap<>();
data.put("nama", "Kelinci"); // diganti kelinci
data.put("warna", "Putih");
data.put("timestamp", System.currentTimeMillis());

NowDBResponse response = nowDb.put(query, data).execute();
Status status = response.status();
```
### GET
#### mencari id
```java
String id = "ID";
NowDBResponse response = nowDb.get("hewan", id).execute();
Animal animal = response.as(Animal.class);
```
#### tampilkan semua
```java
Integer limit = 20;
Integer offset = 5;
NowDBResponse response = nowDb.get("hewan", offset, limit).execute();
List<Animal> animals = response.list(Animal.class);
```
#### dengan kueri
```java
// mencari 5 kelinci
NowDBQuery query = new NowDBQuery("hewan")
				.equal("nama", "Kelinci")
				.limit(5);
NowDBResponse response = nowDb.get(query).execute();
List<Animal> animals = response.list(Animal.class);
```
### DELETE
pasti sudah bisa.
