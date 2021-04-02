package master.msc.config;

import com.blueveery.core.model.BaseEntity;
import com.blueveery.scopes.ProxyInstanceFactory;
import com.blueveery.scopes.ReflectionUtil;
import com.blueveery.scopes.ShortTypeNameIdResolver;
import com.blueveery.scopes.gson.BaseEntityDeserializer;
import com.blueveery.scopes.gson.BaseEntitySerializer;
import com.blueveery.scopes.gson.spring.JsonScopeRequestBodyAdvice;
import com.blueveery.scopes.gson.spring.JsonScopeResponseBodyAdvice;
import com.blueveery.scopes.gson.spring.ScopedGsonHttpMessageConverter;
import com.blueveery.scopes.hibernate.JPASpecificOperationsHibernateImpl;
import com.google.gson.*;
import master.msc.model.MscBaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * The class responsible for providing configuration for serialization and deserialization of data.
 * introduces control of object serialization based on the @JsonScope annotation
 */
@ComponentScan(basePackages = "master.msc.controllers")
@Configuration
public class HttpMessageConvertersConfiguration {

    @Bean
    public HttpMessageConverter<Object> scopedGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        ShortTypeNameIdResolver shortTypeNameIdResolver = new ShortTypeNameIdResolver();

        /* Set path to model, from entities will be serialized */
        shortTypeNameIdResolver.addPackage(MscBaseEntity.class.getPackage());
        ReflectionUtil reflectionUtil = new ReflectionUtil();

        JPASpecificOperationsHibernateImpl jpaSpecificOperations = new JPASpecificOperationsHibernateImpl();
        ProxyInstanceFactory proxyInstanceFactory = new ProxyInstanceFactory(jpaSpecificOperations);
        BaseEntityDeserializer baseEntityDeserializer = new BaseEntityDeserializer(reflectionUtil, shortTypeNameIdResolver, proxyInstanceFactory);
        gsonBuilder.registerTypeHierarchyAdapter(BaseEntity.class, baseEntityDeserializer);


        BaseEntitySerializer baseEntitySerializer = new BaseEntitySerializer(reflectionUtil, shortTypeNameIdResolver, jpaSpecificOperations);
        gsonBuilder.registerTypeHierarchyAdapter(BaseEntity.class, baseEntitySerializer);

        LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, localDateTimeConverter);

        EnumConverter enumConverter = new EnumConverter();
        gsonBuilder.registerTypeHierarchyAdapter(Enum.class, enumConverter);

        /* Create new instance of custom converter */
        GsonHttpMessageConverter scopedGsonHttpMessageConverter = new ScopedGsonHttpMessageConverter();
        scopedGsonHttpMessageConverter.setGson(gsonBuilder.disableHtmlEscaping().create());
        return scopedGsonHttpMessageConverter;
    }


    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }

    @Bean
    public StringHttpMessageConverter StringHttpMessageConverter() {
        return new StringHttpMessageConverter();
    }

    /**
     * Request body converter based on scopes
     */
    @Bean
    public JsonScopeRequestBodyAdvice createRequestBodyAdviceAdapter() {
        return new JsonScopeRequestBodyAdvice();
    }

    /**
     * Response body converter based on scopes
     */
    @Bean
    public JsonScopeResponseBodyAdvice createResponseBodyAdviceAdapter() {
        return new JsonScopeResponseBodyAdvice();
    }

    static class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            long timestamp = jsonElement.getAsLong();
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }


        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
    }

    class EnumConverter implements JsonSerializer<Enum<?>>, JsonDeserializer<Enum<?>> {

        private JsonParser jsonParser = new JsonParser();

        @Override
        public Enum<?> deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            int enumInt = jsonElement.getAsInt();
            if (type instanceof Class && ((Class<?>) type).isEnum()) {
                Class<Enum> enumClass = (Class<Enum>) type;
                if (enumInt >= enumClass.getEnumConstants().length) {
                    throw new IllegalStateException(String.format("%d is out of bound in %s", enumInt, enumClass.getName()));
                }
                return Enum.valueOf(enumClass, enumClass.getEnumConstants()[enumInt].toString());
            } else {
                throw new IllegalArgumentException(String.format("It is not an enum: %s", ((Class<Enum>) type).getName()));
            }
        }


        @Override
        public JsonElement serialize(Enum<?> anEnum, Type type, JsonSerializationContext jsonSerializationContext) {
            return jsonParser.parse(((Integer) anEnum.ordinal()).toString());
        }
    }


}
