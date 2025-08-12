
INSERT INTO Subject (SID, Name) VALUES ('s_alice','Alice');
INSERT INTO Subject (SID, Name) VALUES ('s_bob','Bob');

INSERT INTO Resource (RID, Type) VALUES ('r_med1','medical_record');
INSERT INTO Resource (RID, Type) VALUES ('r_bill1','billing_record');

INSERT INTO Action (AID, Name) VALUES ('a_read','read');
INSERT INTO Action (AID, Name) VALUES ('a_write','write');

INSERT INTO Context (CID, Description) VALUES ('c_hospital','hospital');
INSERT INTO Context (CID, Description) VALUES ('c_home','home');

INSERT INTO SubjectAttr (SID, AttrKey, AttrValue) VALUES ('s_alice','role','doctor');
INSERT INTO SubjectAttr (SID, AttrKey, AttrValue) VALUES ('s_bob','role','nurse');

INSERT INTO ResourceAttr (RID, AttrKey, AttrValue) VALUES ('r_med1','type','medical_record');
INSERT INTO ResourceAttr (RID, AttrKey, AttrValue) VALUES ('r_bill1','type','billing_record');

INSERT INTO ActionAttr (AID, AttrKey, AttrValue) VALUES ('a_read','name','read');
INSERT INTO ActionAttr (AID, AttrKey, AttrValue) VALUES ('a_write','name','write');

INSERT INTO ContextAttr (CID, AttrKey, AttrValue) VALUES ('c_hospital','location','hospital');
INSERT INTO ContextAttr (CID, AttrKey, AttrValue) VALUES ('c_home','location','home');

INSERT INTO Policy (id, name, effect) VALUES (42,'Doctors-read-med','ALLOW');
INSERT INTO PolicyRule (id, policy_id, rule_id, target_type, rule_key, op, rule_value) VALUES
(1,42,1,'subject','role','=','doctor'),
(2,42,2,'action','name','=','read'),
(3,42,3,'resource','type','=','medical_record'),
(4,42,4,'context','location','=','hospital');

INSERT INTO Policy (id, name, effect) VALUES (99,'Nurses-cannot-write','DENY');
INSERT INTO PolicyRule (id, policy_id, rule_id, target_type, rule_key, op, rule_value) VALUES
(5,99,1,'subject','role','=','nurse'),
(6,99,2,'action','name','=','write'),
(7,99,3,'resource','type','=','medical_record');
