package com.laijia.guava.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.io.Files;

public class Demo2 {

	@Test
	public  void testJoin() {
		//use java
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		String str = "";
		for(int i=0; i<list.size(); i++){
			str ="-" +list.get(i);
		}
		System.out.println(str);
		//str 为-aa-bb-cc
		//use guava
		List<String> list2 = new ArrayList<String>();
		list2.add("aa");
		list2.add("bb");
		list2.add("cc");
		String result = Joiner.on("-").join(list2);
		//result为  aa-bb-cc
		System.out.println(result);
	}

	@Test
	public void testSplitToList() {
		//use java
		List<String> list = new ArrayList<String>();
		String a = "1-2-3-4-5-6";
		String[] strs = a.split("-");
		for(int i=0; i<strs.length; i++){
			list.add(strs[i]);
		}
		System.out.println(list);
		//use guava
		String str = "1-2-3-4-5-6";
		List<String> list2 = Splitter.on("-").splitToList(str);
		System.out.println(list2);
		//list为  [1, 2, 3, 4, 5, 6]
		String str2 = "1-2-3-4-  5-  6   ";  
		List<String> list3 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str2);
		System.out.println(list3);

		List<String> list4 = Splitter.on("-").splitToList(str2);
		System.out.println(list4);
	}

	@Test
	public void testFilter() {
		//按照条件过滤
		ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
		Iterable<String> fitered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
		System.out.println(fitered); // [Guava, Java]
		//自定义过滤条件   使用自定义回调方法对Map的每个Value进行操作
		ImmutableMap<String, Integer> m = ImmutableMap.of("begin", 12, "code", 15);
		// Function<F, T> F表示apply()方法input的类型，T表示apply()方法返回类型
		Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {
			public Integer apply(Integer input) {
				if(input>12){
					return input;
				}else{
					return input+1;
				}
			}
		});
		System.out.println(m2);   //{begin=13, code=15}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testCheckArgument() {
		String str = null;
		List<String> list = null;
		int count = 0;
		//use java
		if(list!=null && list.size()>0)
		if(str!=null && str.length()>0)
		if(str !=null && !str.isEmpty())
		//use guava
		if(!Strings.isNullOrEmpty(str))
		//use java
		if (count <= 0) {                                                                                           
		    throw new IllegalArgumentException("must be positive: " + count);         
		}    
		//use guava
		Preconditions.checkArgument(count > 0, "must be positive: %s", count);
	}
	
	@Test
	public void testOrdering() {
		/*natural()	对可排序类型做自然排序，如数字按大小，日期按先后排序
		usingToString()	按对象的字符串形式做字典排序[lexicographical ordering]
		from(Comparator)	把给定的Comparator转化为排序器
		reverse()	获取语义相反的排序器
		nullsFirst()	使用当前排序器，但额外把null值排到最前面。
		nullsLast()	使用当前排序器，但额外把null值排到最后面。
		compound(Comparator)	合成另一个比较器，以处理当前排序器中的相等情况。
		lexicographical()	基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
		onResultOf(Function)	对集合中元素调用Function，再按返回值用当前排序器排序。*/
		
		Person person1 = new Person("aa",14);  //String name  ,Integer age
		Person person2 = new Person("bb",10);
		Person person3 = new Person("bb",13);
		ImmutableList<Person> persons=ImmutableList.of(person1, person2,person3);
		Ordering<Person> byOrdering = Ordering.natural().nullsFirst().reverse().onResultOf(new Function<Person,Integer>(){
			public Integer apply(Person person){
				return person.getAge();
			}
		});
		if(byOrdering.compare(person1, person2)>0){
			System.out.println("person1的年龄大于person2");
		}
		List<Person> sortedCopy = byOrdering.sortedCopy(persons);
		for (Person person : sortedCopy) {
			System.out.println(person.toString());
		}
	}
	
	@Test
	public void testFile() {
		File file = new File("/test.txt");
		@SuppressWarnings("unused")
		List<String> list = null;
		try {
			list = Files.readLines(file, Charsets.UTF_8);
		} catch (Exception e) {
		}
		
		/*
		Files.copy(from,to);  //复制文件
		Files.deleteDirectoryContents(File directory); //删除文件夹下的内容(包括文件与子文件夹)  
		Files.deleteRecursively(File file); //删除文件或者文件夹  
		Files.move(File from, File to); //移动文件
		URL url = Resources.getResource("abc.xml"); //获取classpath根下的abc.xml文件url
		*/
	}
	
	@Test
	public void testCollectionOperate() {
		Set setA = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));  
		Set setB = new HashSet<Integer>(Arrays.asList(4, 5, 6, 7, 8));  
		//并集   
		SetView union = Sets.union(setA, setB);  
		System.out.println("union:");
		Iterator unioniterator = union.iterator();
		while (unioniterator.hasNext())  
		    System.out.println(unioniterator.next());           //union:12345867
		//差集   
		SetView difference = Sets.difference(setA, setB);  
		System.out.println("difference:");  
		Iterator differenceiterator = difference.iterator();
		while (differenceiterator.hasNext()) {
			System.out.println(differenceiterator.next());
		}
		//交集   
		SetView intersection = Sets.intersection(setA, setB);  
		System.out.println("intersection:");
		Iterator intersectioniterator = intersection.iterator();
		while (intersectioniterator.hasNext()) {
			System.out.println(intersectioniterator.next());
		}
	}
	
	@Test
	public void testOptional() {
		Object first = null;
		Object second = 12;
		System.out.println(Optional.fromNullable(first).or(second));
		/*
		try {
			Optional<Object> optional =(Optional<Object>) Optional.of(first).or("");
			System.out.println(optional.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		Optional<Object> optional =Optional.absent();
		System.out.println(optional.isPresent());
//		System.out.println(optional.get());
		System.out.println(optional.orNull());
		
	}
	
}
