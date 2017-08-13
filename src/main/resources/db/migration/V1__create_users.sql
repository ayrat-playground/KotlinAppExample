CREATE TABLE users(
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  created_at   DATETIME     NOT NULL,
  updated_at   DATETIME     NOT NULL,
  name         VARCHAR(512) NOT NULL,
  surname      VARCHAR(512) NOT NULL,

  PRIMARY KEY(id)
) CHARACTER SET = latin1;

CREATE TABLE jobs(
  id    BIGINT       NOT NULL AUTO_INCREMENT,
  name  VARCHAR(512) NOT NULL,

  PRIMARY KEY(id),
  CONSTRAINT unique_job_name UNIQUE(name)
) CHARACTER SET = latin1;

CREATE TABLE user_jobs(
  id       BIGINT NOT NULL AUTO_INCREMENT,
  user_id  BIGINT NOT NULL,
  job_id   BIGINT NOT NULL,

  PRIMARY KEY(id),
  CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT job_id_fk FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE
) CHARACTER SET = latin1;
