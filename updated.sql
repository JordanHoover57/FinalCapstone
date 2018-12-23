Drop table collection_comics;
Drop table collection;
Drop table comic;

delete from app_user;

CREATE TABLE collection(
collection_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
user_name varchar(32) NOT NULL,
collection_name varchar(128) NOT NULL,
access_type varchar(32) NOT NULL
);

CREATE TABLE comic(
id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
comic_vine_id varchar(32) NOT NULL,
volume_id varchar(128) NOT NULL,
volume_name varchar(128) NOT NULL,
issue_number varchar(128) NOT NULL,
image varchar(256) NOT NULL,
thumb_image varchar(256) NOT NULL,
issue_name varchar(128) NULL,
issue_date date NULL,
description text NULL,
condition varchar(128) NULL,
comic_collection_id int NOT NULL

);


CREATE TABLE collection_comics(
collection_id int NOT NULL,
comic_id int NOT NULL
);




AlTER TABLE collection
    ADD CONSTRAINT fk_user_name FOREIGN KEY (user_name) REFERENCES app_user(user_name);

ALTER TABLE collection_comics
        ADD CONSTRAINT fk_collection_id FOREIGN KEY (collection_id) REFERENCES collection(collection_id);

ALTER TABLE collection_comics
        ADD CONSTRAINT fk_comic_id FOREIGN KEY (comic_id) REFERENCES comic(id);

 ALTER TABLE app_user
ADD COLUMN email varchar(256) UNIQUE;

ALTER TABLE app_user
ADD COLUMN f_name varchar(256);

ALTER TABLE app_user
ADD COLUMN l_name varchar(256);       



INSERT INTO app_user (user_name,password,role,salt,email,f_name,l_name)
VALUES ('User','mZfPqaHrTKKLSs3Ex6+GSw==','Standard','wKJcxgKHfZ6ZRdy8NeKJxXrAf/DBRyWjBPUOrxVSV60E4sZucVQWAxWt5GX2nDr+nNOZP6jQcd9XFNCSOLUHS4sOpFcUbTReWUJQms6mn8LOQyMh7BWje2HVouzEeTc34fSJW9/JPLch6YGo2qkSfovWAOqfJHTIJjUPVxMhPJ4=','User@comic.com','Jordan','Hoover');

INSERT INTO app_user (user_name,password,role,salt,email,f_name,l_name)
VALUES ('User2','VeTe5YSpW7ZABMwovg/4pQ==','Premium','YgnzZ9DwhiIKIN4E0fU7k/D0mDXejORQsLQpxEuBsTN856ofEHsM0g+mMfGfESD/JAUpOD1AKrdnAURtAehiV6KRyzFuQH9fqULsu3xs/MTzpiguhoSBimQ7s1hCgr3kzyuxONVYfIh/HX/r3WxqlASRxeUBx7cpIrCQ/YGs5qQ=','User2@comic.com','Eric','Guy');

INSERT INTO app_user (user_name,password,role,salt,email,f_name,l_name)
VALUES ('User3','dN3N22EBR+7OjoqeEbJavg==','Standard','ceA+LaZXn5ybAN4IB8ZzqP5rLmuFNw2ckRN++gGUPSejld0Z744WGAw3Fg9px+LpL0O471l2R58O/0E3qYnkn+bsWbeYEDQJMm805oPiUipIQh4a51SRTQh5AshlgF81c64ih6Xkj91MV2cBbQE8JirP7wgsGRvIq/v5VhYu2Bg=','User3@comic.com','Chuck','Buddy');

INSERT INTO app_user (user_name,password,role,salt,email,f_name,l_name)
VALUES ('User4','qcSJrbDiQEVkfhoIx2dESg==','Premium','JWJF1Y7+/7BPSyY+5pQjKBYnl/NIfJBUZ8G48LJ9CqSdeKX/aBFIYcx5pWErZZd2gFQTy2NSmFkd9EAUogO+LkK9no25RvYALM43cS3elxcej84ixy5rwkt9NTsDvbvvQskdefRwVzY8Qh0zkhV5eapjojaCCOANs4SXwn5oM1M=','User4@comic.com','Barry','Friend');

INSERT INTO collection (user_name, collection_name, access_type)
VALUES ('User2','My Collection', 'Public');

INSERT INTO collection (user_name, collection_name, access_type)
VALUES ('User3','Marvel Collection','Public');

INSERT INTO collection (user_name, collection_name, access_type)
VALUES ('User4','Independent Collection', 'Private');

INSERT INTO collection (user_name, collection_name, access_type)
VALUES ('User2','DC Collection', 'Private');

INSERT INTO collection (user_name, collection_name, access_type)
VALUES ('User3', 'Spiderman Collection','Private');

INSERT INTO collection (user_name, collection_name,  access_type)
VALUES ('User4','Batman Collection', 'Public');


INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name, issue_date, description, condition, comic_collection_id)
VALUES ('112233', '100', 'Spawn Vol 1', '1', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','Spawn Issue 1', '1990-01-08','This is the first issue in the Spawn Series','Great', 2);

INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name,  issue_date, description, condition, comic_collection_id)
VALUES ('445566', '200', 'Green Lantern Vol 2', '54', 'https://images-na.ssl-images-amazon.com/images/I/61%2BdGTEVAKL._SX339_BO1,204,203,200_.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','Green Lantern Issue 54', '1982-06-11','Green Lantern faces his greatest enemey','Poor', 4);

INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name, issue_date, description, condition, comic_collection_id)
VALUES ('778899', '135', 'The Amazing Spiderman Vol 3', '23', 'https://i.annihil.us/u/prod/marvel/i/mg/b/90/5b3feb60f1a30/clean.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','Amazing Spiderman Issue 23', '1995-07-09','Spiderman faces the vulture','Good', 5);

INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name, issue_date, description, condition, comic_collection_id)
VALUES ('223344', '500', 'Detective Comics Vol 1', '500','https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/575921/575921._SX360_QL80_TTD_.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','Detective Comics Issue 500', '2000-03-04','Batman solves a crime','Great', 6);

INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name, issue_date, description, condition, comic_collection_id)
VALUES ('334455', '58', 'Wonder Woman Vol 3', '322','https://static.comicvine.com/uploads/original/3/39001/4233102-ww184adamhughes.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','Wonder Woman Issue 322', '2001-03-05','Wonder Woman does some stuff','Ok', 8);

INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, issue_name, issue_date, description, condition, comic_collection_id)
VALUES ('889900', '89', 'V for Vendetta Vol 1', '1', 'https://upload.wikimedia.org/wikipedia/en/thumb/c/c0/V_for_vendettax.jpg/220px-V_for_vendettax.jpg', 'https://image.ibb.co/g24uvv/spawn_thank_you.jpg','V for Vendetta Issue 1','1989-01-04','V defeats the fascists','Great', 3);


INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('1','1');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('1','2');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('2','1');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('2','2');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('3','3');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('3','4');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('4','3');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('4','4');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('5','5');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('5','6');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('6','5');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('6','6');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('6','5');

INSERT INTO collection_comics (collection_id, comic_id)
VALUES ('6','1');

