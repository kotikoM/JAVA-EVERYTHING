--@Block
INSERT INTO User (user_name, password, mail, latitude, longitude)
VALUES
    ('john_doe', 'password123', 'john.doe@email.com', 42.123456, -78.987654),
    ('jane_smith', 'p@ssw0rd', 'jane.smith@email.com', 37.789012, -122.456789),
    ('bob_carter', 'securepass', 'bob.carter@email.com', 51.345678, -0.123456),
    ('alice_johnson', 'letmein', 'alice.johnson@email.com', 32.456789, -117.123456),
    ('sarah_wilson', 'sarah123', 'sarah.wilson@email.com', 40.123456, -75.987654),
    ('michael_jackson', 'mj_pass', 'michael.jackson@email.com', 33.789012, -118.456789),
    ('emily_brown', 'password123', 'emily.brown@email.com', 48.345678, -4.123456),
    ('david_martin', 'martin123', 'david.martin@email.com', 52.456789, -2.123456);


--@Block
INSERT INTO Task (name, description, due_date, status, userID)
VALUES
    ('Plant Seeds in Field A', 'Sow wheat seeds in Field A', '2023-10-15', 'Not Complete', 1),
    ('Harvest Corn in Field B', 'Harvest ripe corn in Field B', '2023-10-20', 'Not Complete', 2),
    ('Feed the Chickens', 'Provide food to the farm chickens', '2023-10-18', 'Not Complete', 3),
    ('Inspect Irrigation System', 'Check irrigation system for issues', '2023-10-25', 'Not Complete', 4),
    ('Repair Barn Roof', 'Fix the barn roof leaks', '2023-10-22', 'Not Complete', 5),
    ('Prepare Cattle for Market', 'Get cattle ready for market', '2023-10-17', 'Not Complete', 6),
    ('Prune Apple Trees', 'Trim apple trees in orchard', '2023-10-28', 'Not Complete', 7),
    ('Clean Farm Equipment', 'Clean tractors and machinery', '2023-10-30', 'Not Complete', 8),
    ('Plant Sunflowers', 'Sow sunflower seeds in garden', '2023-10-15', 'Not Complete', 1),
    ('Test Soil pH Levels', 'Check soil pH levels in all fields', '2023-10-18', 'Not Complete', 2),
    ('Farmers Meeting', 'Attend a meeting with local farmers', '2023-10-20', 'Not Complete', 3),
    ('Update Farm Records', 'Organize and update farm records', '2023-10-27', 'Not Complete', 4),
    ('Hire Seasonal Workers', 'Recruit temporary workers for harvest', '2023-10-15', 'Not Complete', 5),
    ('Inspect Livestock Health', 'Check the health of livestock', '2023-10-22', 'Not Complete', 6),
    ('Prepare Farm Stand', 'Set up the farm stand for customers', '2023-10-19', 'Not Complete', 7);



--@Block
INSERT INTO Financial_Record (date, type, amount, description, userID)
VALUES
    ('2023-10-10', 'Income', 1500.00, 'Crop sales (Wheat)', 1),
    ('2023-10-11', 'Expense', 250.50, 'Purchase of seeds (Corn)', 2),
    ('2023-10-12', 'Expense', 135.75, 'Irrigation system repair', 3),
    ('2023-10-13', 'Income', 900.00, 'Harvested vegetables sales', 4),
    ('2023-10-14', 'Expense', 75.25, 'Fertilizer and pesticides', 1),
    ('2023-10-15', 'Income', 500.00, 'Fruit sales (Apples)', 2),
    ('2023-10-16', 'Expense', 50.00, 'Tractor maintenance', 3),
    ('2023-10-17', 'Expense', 120.00, 'Barn renovation', 4),
    ('2023-10-18', 'Income', 750.00, 'Farm tours revenue', 1),
    ('2023-10-19', 'Expense', 200.00, 'Crop insurance', 2),
    ('2023-10-20', 'Income', 600.00, 'Market garden sales', 3),
    ('2023-10-21', 'Expense', 45.99, 'Equipment maintenance', 4),
    ('2023-10-22', 'Income', 3000.00, 'Selling honey products', 1),
    ('2023-10-23', 'Expense', 40.00, 'Fuel for farm vehicles', 2),
    ('2023-10-24', 'Expense', 120.00, 'Farm stand setup and maintenance', 3);


--@Block
INSERT INTO Employee (first_name, last_name, contact_info, role, salary, userID)
VALUES
    ('John', 'Doe', 'john.doe@email.com, (123) 456-7890', 'Farm Manager', 45000.00, 1),
    ('Jane', 'Smith', 'jane.smith@email.com, (456) 789-1234', 'Agricultural Technician', 35000.00, 2),
    ('Bob', 'Carter', 'bob.carter@email.com, (789) 123-4567', 'Harvest Supervisor', 40000.00, 3),
    ('Alice', 'Johnson', 'alice.johnson@email.com, (234) 567-8901', 'Farmhand', 30000.00, 4),
    ('Sarah', 'Wilson', 'sarah.wilson@email.com, (567) 890-1234', 'Market Manager', 38000.00, 5),
    ('Michael', 'Jackson', 'michael.jackson@email.com, (890) 123-4567', 'Livestock Specialist', 42000.00, 6),
    ('Emily', 'Brown', 'emily.brown@email.com, (123) 234-5678', 'Horticulturist', 36000.00, 7),
    ('David', 'Martin', 'david.martin@email.com, (456) 567-8901', 'Equipment Operator', 32000.00, 8);


--@Block
INSERT INTO Inventory_Item (itemID, name, quantity, price, employeeID)
VALUES
    (1, 'Tractor', 2, 15000.00, 1),
    (2, 'Plow', 3, 5000.00, 2),
    (3, 'Seeds (Wheat)', 500, 1000.00, 3),
    (4, 'Fertilizer (Organic)', 50, 500.00, 4),
    (5, 'Irrigation System', 1, 8000.00, 5),
    (6, 'Harvesting Equipment', 2, 12000.00, 6),
    (7, 'Storage Barrels', 10, 100.00, 7),
    (8, 'Gardening Tools', 20, 400.00, 8);


--@Block
INSERT INTO Crop (name, species, planting_date, harvest_date, description, requirements)
VALUES
    ('Wheat', 'Triticum aestivum', '2023-04-15', '2023-07-30', 'Winter wheat for bread production.', 'Well-drained soil, full sun'),
    ('Corn', 'Zea mays', '2023-05-01', '2023-09-15', 'Sweet corn for local market.', 'Rich soil, regular watering'),
    ('Tomatoes', 'Solanum lycopersicum', '2023-04-10', '2023-08-25', 'Roma tomatoes for canning.', 'Warm climate, staking'),
    ('Apples', 'Malus domestica', '2023-03-20', '2023-10-30', 'Fuji apples for the orchard.', 'Fertile soil, pruning'),
    ('Potatoes', 'Solanum tuberosum', '2023-04-05', '2023-09-10', 'Yukon Gold potatoes for the farm.', 'Loose soil, hilling'),
    ('Carrots', 'Daucus carota', '2023-04-01', '2023-08-20', 'Organic carrots for local markets.', 'Sandy soil, regular thinning'),
    ('Blueberries', 'Vaccinium corymbosum', '2023-04-10', '2023-07-20', 'Fresh blueberries for local markets.', 'Acidic soil, regular pruning'),
    ('Strawberries', 'Fragaria x ananassa', '2023-03-15', '2023-06-30', 'Sweet strawberries for pick-your-own.', 'Well-drained soil, mulching'),
    ('Pumpkins', 'Cucurbita pepo', '2023-05-10', '2023-10-31', 'Jack-o-lantern pumpkins for Halloween.', 'Rich soil, space for vines'),
    ('Lettuce', 'Lactuca sativa', '2023-03-01', '2023-05-30', 'Crisp lettuce for salads and sandwiches.', 'Cool weather, regular watering'),
    ('Grapes', 'Vitis vinifera', '2023-04-20', '2023-09-15', 'Table grapes for vineyard.', 'Well-drained soil, trellising'),
    ('Cabbage', 'Brassica oleracea', '2023-04-01', '2023-07-20', 'Savoy cabbage for coleslaw.', 'Rich soil, pest control'),
    ('Cucumbers', 'Cucumis sativus', '2023-05-01', '2023-08-20', 'Crunchy cucumbers for pickles.', 'Warm climate, trellising'),
    ('Cherries', 'Prunus avium', '2023-04-10', '2023-07-15', 'Sweet cherries for pies and jams.', 'Well-drained soil, pruning'),
    ('Onions', 'Allium cepa', '2023-03-15', '2023-07-30', 'Yellow onions for cooking.', 'Loose soil, proper spacing'),
    ('Peppers', 'Capsicum annuum', '2023-04-05', '2023-08-25', 'Bell peppers for colorful salads.', 'Warm climate, regular watering'),
    ('Melons', 'Cucumis melo', '2023-05-10', '2023-09-10', 'Sweet cantaloupes for dessert.', 'Rich soil, space for vines'),
    ('Sunflowers', 'Helianthus annuus', '2023-05-01', '2023-08-15', 'Ornamental sunflowers for farm decoration.', 'Well-drained soil, full sun'),
    ('Zucchinis', 'Cucurbita pepo', '2023-04-20', '2023-08-20', 'Green zucchinis for summer recipes.', 'Rich soil, space for growth');

--@Block
INSERT INTO Harvest_Entry (employeeID, cropID, quantity_kg, date, notes)
VALUES
    (1, 1, 500.350, '2023-07-30', 'Harvested wheat for bread production.'),
    (2, 2, 100.250, '2023-09-15', 'Picked sweet corn for local market.'),
    (3, 3, 80.500, '2023-08-25', 'Collected Roma tomatoes for canning.'),
    (4, 4, 350.750, '2023-10-30', 'Harvested Fuji apples from the orchard.'),
    (1, 5, 200.150, '2023-09-10', 'Dug up Yukon Gold potatoes for storage.'),
    (2, 6, 300.600, '2023-08-20', 'Gathered carrots for local markets.'),
    (3, 7, 40.750, '2023-07-20', 'Picked fresh blueberries for sale.'),
    (4, 8, 75.800, '2023-06-30', 'Harvested ripe strawberries for customers.'),
    (1, 9, 50.500, '2023-10-31', 'Collected pumpkins for Halloween season.'),
    (2, 10, 30.750, '2023-05-30', 'Harvested lettuce for salads.'),
    (3, 11, 70.250, '2023-09-15', 'Picked grapes from the vineyard.'),
    (4, 12, 45.600, '2023-07-20', 'Harvested Savoy cabbage for coleslaw.'),
    (1, 13, 90.300, '2023-08.20', 'Collected cucumbers for pickling.'),
    (2, 14, 110.500, '2023-07.15', 'Picked sweet cherries for pies.'),
    (3, 15, 40.750, '2023-07.30', 'Dug up yellow onions for storage.'),
    (4, 16, 65.600, '2023-08.25', 'Harvested bell peppers for sale.'),
    (1, 17, 30.750, '2023-09.10', 'Picked cantaloupes for customers.'),
    (2, 18, 20.500, '2023-08.15', 'Harvested ornamental sunflowers for farm decoration.'),
    (3, 19, 90.300, '2023-08.20', 'Collected green zucchinis for recipes.');


--@block
select cropID, name from crop



