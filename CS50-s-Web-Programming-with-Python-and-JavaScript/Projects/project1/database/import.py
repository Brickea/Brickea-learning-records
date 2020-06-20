"""
Import books csv file into database
"""
import os
import csv
from sqlalchemy import create_engine,text
from sqlalchemy.orm import scoped_session, sessionmaker
from env_init import database_flask_env_init

database_flask_env_init()

# Set up database
engine = create_engine(os.getenv("DATABASE_URL"))
db = scoped_session(sessionmaker(bind=engine))

def import_csv_file():
    # print(os.getcwd()) # Check current root file path
    with open("./database/books.csv") as f:
        reader = csv.reader(f)
        sql = text('''insert into books (isbn,title,author,year)
        values(:isbn,:title,:author,:year)''')

        for isbn, title, author, year in reader:
            para = {"isbn":isbn,"title":title,"author":author,"year":year}
            print(para)
            db.execute(sql,para)
        db.commit()

def main():
    import_csv_file()

if __name__ == "__main__":
    main()