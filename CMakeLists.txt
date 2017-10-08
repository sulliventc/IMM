cmake_minimum_required(VERSION 3.9.3)
project(IMM)

set(CMAKE_CXX_STANDARD 17)

find_package(Boost 1.65 COMPONENTS filesystem REQUIRED)
include_directories(${Boost_INCLUDE_DIRS})

find_package(wxWidgets COMPONENTS net gl core base REQUIRED)
include(${wxWidgets_USE_FILE})

find_package(CURL REQUIRED)
include_directories(${CURL_INCLUDE_DIRS})

find_package(zlib REQUIRED)
include_directories(${ZLIB_INCLUDE_DIRS})
find_library(ZIPPER_LIBRARY
        NAMES libZipper-static.a
        PATHS /usr/local/lib /usr/lib)
include_directories(/usr/local/include/zipper)

set(SOURCE_FILES src/main.cpp include/SerializedTypes.h src/Utility.cpp include/Utility.h src/SmapiControl.cpp include/SmapiControl.h)
add_executable(IMM ${SOURCE_FILES})
target_link_libraries(IMM ${Boost_LIBRARIES} ${wxWidgets_LIBRARIES} ${CURL_LIBRARIES} ${ZLIB_LIBRARIES} ${ZIPPER_LIBRARY})
