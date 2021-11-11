delete from Users.User;

INSERT INTO Users.User
(FIRSTNAME, LASTNAME, MIDDLENAME, USERLOGIN, ISBLOCKED, USERPASSWORD)
VALUES
(
    --MD5 ("Ivanov_cool_man") = 3a41de8bb0df1239020609dc617e8421
    'Иван', 'Иванов', 'Иванович', 'ivanov_i_i', false, '3a41de8bb0df1239020609dc617e8421'
),
(
    --MD5 ("john_doe_typical_real_guy") = 3fe1fbdc179cd078b60663040820008a
    'John', 'Doe', 'Sully', 'j_doe_s', false, '3fe1fbdc179cd078b60663040820008a'
),
(
    --MD5 ("im_on_blue_period") = c624a97587f2ddc148da72988ad319f5
    'Pablo', 'Picasso', 'Diego José Francisco de Paula Juan Nepomuceno María de los Remedios Cipriano de la Santísima Trinidad Mártir Patricio Ruiz y Picasso', 'pic_d', false, 'c624a97587f2ddc148da72988ad319f5'
),
(
    --MD5 ("red_corner_is_black_now") = b5a51ec4997b6b72bf6d5c763401d766
    'Каземир', 'Малевич', '', 'square_black_man', true, 'b5a51ec4997b6b72bf6d5c763401d766'
);

delete from Users.Switch;

INSERT INTO Users.Switch
(SWITCHNAME, VALUE)
VALUES
(
    'dbo_access_userverify', true
),
(
    'dbo_access_usercontroller', false
);