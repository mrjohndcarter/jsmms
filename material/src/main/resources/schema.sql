drop table if exists material;

create table if not exists material (
    materialId IDENTITY(1,1) NOT NULL PRIMARY KEY,
    description VARCHAR(50),
    materialType VARCHAR(50)
);