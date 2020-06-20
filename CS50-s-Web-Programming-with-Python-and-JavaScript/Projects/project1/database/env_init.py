import os


def database_flask_env_init():
    os.environ["FLASK_APP"] = "application.py"
    os.environ["FLASK_ENV"] = "development"
    os.environ["DATABASE_URL"] = "postgres://kweybweorilfbm:ff6c7e066fa29714f0c33c05667ed0f48f4266df2f7aa0b7337fccfb6b3230ba@ec2-34-193-117-204.compute-1.amazonaws.com:5432/d5mmlr285uef28"

