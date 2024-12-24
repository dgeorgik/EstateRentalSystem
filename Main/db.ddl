CREATE TABLE public.users_grpc (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NULL,
	email varchar NULL
);


CREATE TABLE public.country_grpc (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	country varchar NULL
);

CREATE TABLE public.city_grpc (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	city varchar NULL
);


CREATE TABLE public.renovation_grpc (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	renovation varchar NULL
);


CREATE TABLE public.announcement_grpc (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	id_country_grpc int4 NOT NULL,
	id_city_grpc int4 NOT NULL,
	id_renovation_grpc int4 NOT NULL,
	number_of_rooms int4 NULL,
	area int4 NULL,
	floor int4 NULL
);