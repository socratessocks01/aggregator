CREATE TABLE data_source (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO data_source(name) values('YOUTUBE');
INSERT INTO data_source(name) values('REDDIT');
INSERT INTO data_source(name) values('TWITTER');

CREATE TABLE entity (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE live_source (
    id SERIAL PRIMARY KEY,              -- Auto-incrementing primary key
    source_id INT NOT NULL,             -- Foreign key referencing the sources table
    entity_id INT NOT NULL,             -- Foreign key referencing the entities table
    is_active BOOLEAN NOT NULL DEFAULT TRUE, -- Indicates if the mapping is active
    source_url VARCHAR(255) NOT NULL,   -- URL of the source
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last updated timestamp

    -- Foreign key constraints
    CONSTRAINT fk_source FOREIGN KEY (source_id) REFERENCES data_source(id) ON DELETE CASCADE,
    CONSTRAINT fk_entity FOREIGN KEY (entity_id) REFERENCES entity(id) ON DELETE CASCADE
);


CREATE TABLE content_type (
      id SERIAL PRIMARY KEY,
      source_id INT NOT NULL,             -- Foreign key referencing the sources table
      type VARCHAR(255) NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation timestamp
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last updated timestamp

      -- Foreign key constraints
      CONSTRAINT fk_source FOREIGN KEY (source_id) REFERENCES data_source(id) ON DELETE CASCADE
)

CREATE TABLE raw_content(
    id SERIAL PRIMARY KEY,
    content_original_id VARCHAR(255) NOT NULL,
    live_source_id INT NOT NULL,   -- Foreign key referencing the live_source table
    content_type_id INT NOT NULL,  -- Foreign key referencing the content_type table
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last updated timestamp

    -- Foreign key constraints
    CONSTRAINT fk_live_source FOREIGN KEY (live_source_id) REFERENCES live_source(id) ON DELETE CASCADE,
    CONSTRAINT fk_content_type FOREIGN KEY (content_type_id) REFERENCES content_type(id) ON DELETE CASCADE
);
