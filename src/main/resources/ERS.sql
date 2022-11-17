create table employee (
employee_id numeric primary key, -- change to username / change numeric to varchar(255)
employee_email varchar(255), -- can change the amount inside varchar to a smaller number.
employee_isManager boolean default false, --use boolean and DEFAULT = false
employee_pwd varchar(255) -- can change the amount inside varchar to a smaller number. maybe change PWD to something else???
);

-- change requests to reimbursements or tickets??
-- request_id -> r_id, r_type

create table requests (
amount numeric,
request_id numeric primary key,  -- numeric to serial
request_type varchar(255),
status varchar(255), -- can change the amount inside varchar to a smaller number.
requester numeric, -- change to varchar(255)
foreign key (requester) references employee(employee_id)  -- be sure to change employee(employee_id) to employee(e_id)
);