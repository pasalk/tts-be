SELECT setval('language_id_seq', (SELECT MAX(id) FROM language));
SELECT setval('voice_id_seq', (SELECT MAX(id) FROM voice));




