package com.examen.prueba.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.retencion_ms_config.topic1}")
    private String tiempoEnServidor;
    @Value("${kafka.segment_bytes_config.topic1}")
    private String tamanioMaxPorMensaje;
    @Value("${kafka.name.topic1}")
    private String nombreTopicUno;
    @Value("${kafka.partitions.topic1}")
    private int numParticionesUno;
    @Value("${kafka.replicas.topic1}")
    private int numReplicasUno;

    @Value("${kafka.name.topic2}")
    private String nombreTopicDos;
    @Value("${kafka.partitions.topic2}")
    private int numParticionesDos;
    @Value("${kafka.replicas.topic2}")
    private int numReplicasDos;

    @Bean
    public NewTopic generateTopicUno(){
        Map<String, String> configurations = new HashMap<>();

        // delete (borra el mensaje), copact (Mantiene el mensaje más actual)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Cuanto tiempo permaneceran los mensajes en el server, por ejemplo un díaa con: 86400000 - defecto -1 osea nunca
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, tiempoEnServidor);
        // Tamanio maximo de cada mensaje - por defecto viene 1 Megabyte
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, tamanioMaxPorMensaje);

        return TopicBuilder.name(nombreTopicUno)
                .partitions(numParticionesUno)
                .replicas(numReplicasUno)
                .configs(configurations)
                .build();
    }

    @Bean
    public NewTopic generateTopicDos(){
        Map<String, String> configurations = new HashMap<>();

        // delete (borra el mensaje), copact (Mantiene el mensaje más actual)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Cuanto tiempo permaneceran los mensajes en el server, por ejemplo un díaa con: 86400000 - defecto -1 osea nunca
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, tiempoEnServidor);
        // Tamanio maximo de cada mensaje - por defecto viene 1 Megabyte
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, tamanioMaxPorMensaje);

        return TopicBuilder.name(nombreTopicDos)
                .partitions(numParticionesDos)
                .replicas(numReplicasDos)
                .configs(configurations)
                .build();
    }
}
