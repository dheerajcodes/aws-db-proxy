# API Proxy for AWS Data Stores
This is an API server written for querying AWS Aurora RDS and Redshift data stores from within AWS VPC. This application must be hosted within AWS VPC with public access enabled. This allows connections from outside AWS VPC to API Server within VPC and thus enables querying data stores available within VPC.

### Environment Variables
* `AURORA_HOST` - AWS Aurora RDS host address
* `AURORA_PORT` - AWS Aurora RDS host port
* `AURORA_DB` - AWS Aurora RDS database
* `AURORA_USERNAME` - AWS Aurora RDS username
* `AURORA_PASSWORD` - AWS Aurora RDS password
* `REDSHIFT_HOST` - AWS Redshift Warehouse host address
* `REDSHIFT_PORT` - AWS Redshift Warehouse host port
* `REDSHIFT_DB` - AWS Redshift Warehouse database
* `REDSHIFT_USERNAME` - AWS Redshift Warehouse master username
* `REDSHIFT_PASSWORD` - AWS Redshift Warehouse master password