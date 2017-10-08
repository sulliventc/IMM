//
// Created by Colten Sullivent on 10/3/17.
//

#ifndef IMM_UTILITY_H
#define IMM_UTILITY_H

#include <boost/filesystem.hpp>

enum OS {UNKNOWN, WIN, MACOS, LINUX};

class Utility {
public:

  static boost::filesystem::path getExecDir();
private:
  // Disallow instantiation
  Utility();

  static OS currentOS;
  static boost::filesystem::path execDir;
  static boost::filesystem::path modDir;
  static boost::filesystem::path contentDir;

  static OS getCurrentOS();
};


#endif //IMM_UTILITY_H
