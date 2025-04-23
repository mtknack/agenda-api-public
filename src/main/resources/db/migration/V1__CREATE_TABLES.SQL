CREATE TABLE agenda_api (
	id_agenda_api int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	id_agenda_google int8 NOT NULL,
	tx_title varchar(100) NOT NULL,
	tx_description varchar(10000) NULL,
	dt_start_date_time timestamp NOT NULL,
	tx_time_zone varchar(15) NOT NULL,
	dt_end_date_time timestamp NOT NULL,
	tx_location varchar(50) NULL,
	nm_minutes_notification int4 NULL,
	tx_type varchar(30) NULL,
	nm_importance int4 NULL,
	CONSTRAINT agenda_api_pkey PRIMARY KEY (id_agenda_api)
);


CREATE TABLE task (
	id_task int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	tx_title varchar(100) NOT NULL,
	tx_description varchar(10000) NULL,
	dt_start_date_time timestamp NOT NULL,
	tx_time_zone varchar(15) NOT NULL,
	dt_end_date_time timestamp NOT NULL,
	tx_location varchar(50) NULL,
	nm_minutes_notification int4 NULL,
	tx_type varchar(30) NULL,
	nm_importance int4 NULL,
	id_agenda_api int8 NULL,
	CONSTRAINT task_pkey PRIMARY KEY (id_task)
);

ALTER TABLE task ADD CONSTRAINT fk_id_agenda_api FOREIGN KEY (id_agenda_api) REFERENCES agenda_api(id_agenda_api);

CREATE TABLE user_config_style (
	id_user_config_style int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	tx_text_color varchar(10) NULL,
	tx_primary_color varchar(10) NULL,
	tx_secondary_color varchar(10) NULL,
	tx_tertiary_color varchar(10) NULL,
	tx_background_color varchar(10) NULL,
	CONSTRAINT user_config_style_pkey PRIMARY KEY (id_user_config_style)
);


CREATE TABLE user_config (
	id_user_config int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	id_user_config_style int8 NULL,
	bl_integrate_google_agenda bool DEFAULT false NULL,
	CONSTRAINT user_config_pkey PRIMARY KEY (id_user_config)
);

ALTER TABLE user_config ADD CONSTRAINT fk_id_user_config_style FOREIGN KEY (id_user_config_style) REFERENCES user_config_style(id_user_config_style);