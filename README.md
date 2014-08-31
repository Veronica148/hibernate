hibernate
=========

Usage hibernate without Spring

Entities:
* <b>Service</b>
* <b>Employee</b>
* <b>Record</b>

Tables
* Tables <i>Service</i> and <i>Employee</i> link as M:M (One employee can provide different services, and service can be provided by several employees).

* Tables <i>Record</i> and <i>Employee</i> link as M:M (One person can make appointment with several masters, and one employee can serve several persons).

DAO layer for work with database is present.
Scripts for creating tables is in the <i>mysql_script.txt</i>
