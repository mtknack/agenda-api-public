package com.mtknack.agenda;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Configuration
public class GoogleCalendarConfig {

    @Bean
    public Calendar googleCalendarService() throws IOException {
        // Carregar credenciais do arquivo JSON localizado na pasta resources
        InputStream credentialsStream = new ClassPathResource("credentials/credentials3.json").getInputStream();
        
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));
        
        HttpCredentialsAdapter requestInitializer = new HttpCredentialsAdapter(credentials);

        // Configurar o serviço do Calendar
        return new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Your Application Name")
                .build();
    }
}

