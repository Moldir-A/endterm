
DROP TABLE IF EXISTS dreams;
DROP TABLE IF EXISTS emotions;

CREATE TABLE emotions (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL
);

CREATE TABLE dreams (
                        id SERIAL PRIMARY KEY,
                        type VARCHAR(20) NOT NULL,       
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        intensity INTEGER CHECK (intensity >= 1 AND intensity <= 10),
                        dream_date DATE NOT NULL,
                        extra_param BOOLEAN DEFAULT FALSE 
);
CREATE TABLE IF NOT EXISTS emotions (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS dreams (
                                      id SERIAL PRIMARY KEY,
                                      type VARCHAR(20) NOT NULL,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      intensity INTEGER,
                                      dream_date DATE NOT NULL,
                                      extra_param BOOLEAN DEFAULT FALSE
);

INSERT INTO emotions (name) VALUES ('Joy'), ('Fear'), ('Anxiety'), ('Excitement') ON CONFLICT DO NOTHING;
INSERT INTO emotions (name) VALUES ('Joy'), ('Fear'), ('Anxiety'), ('Excitement');
