CREATE TABLE events (
    id UUID PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    details VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    maximum_attendees INTEGER NOT NULL,
);

CREATE UNIQUE INDEX events_slug_key on events(slug);