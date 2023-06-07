# Setup
## Database Setup
- Create main database mentioned in application.properties `codingworld`
```text
database.name=codingworld
```
- Run the application

### Now once the tables and hibernate_schema are generated, 
- Create the database for each mentioned tenant in `datasource` table. We can get this from 
`select * from datasource`
- Now, all the tables from main table except `datasource` must be replicated in tenant databases.
- We can do this from `show create table <table-name>`
- and apply the create query to tenant databases (this should be done for `hibernate_sequence` too)

### Error that can occur
Error message: `could-not-read-a-hi-value-you-need-to-populate-the-table-hibernate`

This message occurs when hibernate sequence is not initiated in table `hibernate_sequence`. To solve this,
we need to create one row for each `hibernate_sequence` table for each tenant database.
- This can be done with sql command `INSERT INTO hibernate_sequence (next_val) VALUES (0);`
- Verify the database `hibernate_sequence` tables have one row with `id=0`;