-- liquibase formatted sql

-- changeset mmayakina:1
CREATE TABLE postgres
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    chatId INT NOT NULL,
    message TEXT NOT NULL,
    notificationDateTime TIMESTAMP NOT NULL
);
