'''
   conditionals.py
   Jeff Ondich, 2013-01-03
   Anna Rafferty, 2016-08-24 (conversion to python3)
   Layla Oesper, 2017-08-30

   A tiny illustration of if-statements and console input.

   Intended as the Python half of parallel examples in Python and
   Java. See Conditionals.java.
'''

numberString = input('Number, please: ')
number = int(numberString)

if number > 100:
    print('Gosh, {} is a big number'.format(number))
elif number == 47:
    print('47 is my lucky number')
elif number >= 0:
    print('{} is pretty little'.format(number))
else:
    print('{} is negative'.format(number))
