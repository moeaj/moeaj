package org.moeaj.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.moeaj.core.solution.MBinarySolution;
import org.moeaj.core.solution.MIntegerSolution;
import org.moeaj.core.solution.MSolution;
import org.moeaj.json.deserializer.BinarySetDeserializer;
import org.moeaj.json.serializer.BinarySetSerializer;
import org.uma.jmetal.util.binarySet.BinarySet;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class ConverterUtils {

    public static int[] toIntegerArray(List<Integer> list) {

        Preconditions.checkNotNull(list, "The array should not be null");

        return list.stream().mapToInt(x -> x).toArray();
    }

    public static double[] toDoubleArray(List<Double> list) {

        Preconditions.checkNotNull(list, "The array should not be null");

        return list.stream().mapToDouble(x -> x).toArray();
    }

    public static String[] toStringArray(List<String> list) {

        Preconditions.checkNotNull(list, "The array should not be null");

        return list.stream().toArray(String[]::new);
    }

    public static List<Integer> toIntegerList(int[] array) {

        Preconditions.checkNotNull(array, "The array should not be null");

        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public static List<Double> toDoubleList(double[] array) {

        Preconditions.checkNotNull(array, "The array should not be null");

        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public static List<String> toStringList(String[] array) {

        Preconditions.checkNotNull(array, "The array should not be null");

        return new ArrayList<>(Arrays.asList(array));
    }
    
	public static String toId(String text) {

		Preconditions.checkNotNull(text, "The text should not be null");

		return text.replaceAll("[^A-Za-z0-9]", "-").toLowerCase();
	}
	
	public static String toJson(Object object) {
		return toJson(object, null);
	}

	@SuppressWarnings("rawtypes")
	public static String toJson(Object object, Type typeOfSrc) {
		
		Preconditions.checkNotNull(object, "The object should not be null");
		
		RuntimeTypeAdapterFactory<MSolution> msolutionAdapter = RuntimeTypeAdapterFactory.of(MSolution.class, "type")
			    .registerSubtype(MBinarySolution.class, MBinarySolution.class.getName())
			    .registerSubtype(MIntegerSolution.class, MIntegerSolution.class.getName());
		
		Gson gson = new GsonBuilder()
				.serializeSpecialFloatingPointValues()
				.registerTypeAdapterFactory(msolutionAdapter)
				.registerTypeAdapter(BinarySet.class, new BinarySetSerializer())
				.create();
		
		if (typeOfSrc == null) {
			return gson.toJson(object);
		}

		return gson.toJson(object, typeOfSrc);
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> T fromJson(String content, Class<T> classOfT) {
		
		Preconditions.checkNotNull(content, "The content should not be null");
		Preconditions.checkNotNull(classOfT, "The class of T should not be null");
		
		RuntimeTypeAdapterFactory<MSolution> msolutionAdapter = RuntimeTypeAdapterFactory.of(MSolution.class, "type")
			    .registerSubtype(MBinarySolution.class, MBinarySolution.class.getName())
			    .registerSubtype(MIntegerSolution.class, MIntegerSolution.class.getName());
		
		Gson gson = new GsonBuilder()
				.serializeSpecialFloatingPointValues()
				.registerTypeAdapterFactory(msolutionAdapter)
				.registerTypeAdapter(BinarySet.class, new BinarySetDeserializer())
				.create();
		
		return gson.fromJson(content, classOfT);
	}
}
