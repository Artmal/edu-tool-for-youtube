-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO users VALUES (1, 'Artmal', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);

-- ------------------------------------------------------------------------

-- Table: playlists
CREATE TABLE playlists(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  channel VARCHAR(255) NOT NULL,
  link VARCHAR(255) NOT NULL,
  subject_id INT NOT NULL,
  amount_of_completed_videos INT,
  amount_of_videos INT,

  UNIQUE (name, link)
)
  ENGINE = InnoDB;

-- Table for mapping users to their studying playlists: user_playlists
CREATE TABLE users_playlists(
  user_id INT NOT NULL,
  playlist_id INT NOT NULL,

  PRIMARY KEY(user_id, playlist_id)
)
  ENGINE = InnoDB;

CREATE TABLE videos(
  id int AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  video_code VARCHAR(255) NOT NULL,
  duration VARCHAR(255) NOT NULL,
  is_completed BOOLEAN NOT NULL,
  level_of_understanding INT DEFAULT 0,
  playlist_id int NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE video_notes(
  id int AUTO_INCREMENT PRIMARY KEY,
  note TEXT NOT NULL,
  date VARCHAR(30) NOT NULL,
  video_id int NOT NULL,

  FOREIGN KEY(video_id) REFERENCES videos(id)
)
  ENGINE = InnoDB;

CREATE TABLE subjects(
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  user_id INT NOT NULL,
  amount_of_playlists INT,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;