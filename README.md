# Scala UDFs in Spark

Example code for creating Scala UDFs for Apache Spark. This is the code for the following [slides](https://docs.google.com/presentation/d/11JHEUUEjlAZzXO0IRuZoHbTaH3Cv2-hM9GCRbdVgv1I/edit?usp=sharing).

This is intended to be used with the Climate Change: Earth Surface Temperature Data [dataset](https://www.kaggle.com/berkeleyearth/climate-change-earth-surface-temperature-data) available on [Kaggle](https://www.kaggle.com/).

## Setup 
1. Clone this repo.
2. Download the [data](https://www.kaggle.com/berkeleyearth/climate-change-earth-surface-temperature-data). Extract the data into the `data/` directory in the project root.
3. Run `sbt console`. In the console, start with: 
   ```bash
   scala> import com.github.mvinesn.Climate._
   scala> val df = loadDataframe()
   ```

By default only one of the tables in the dataset is loaded. Feel free to experiment further.

If you find an error, let me know!
  



