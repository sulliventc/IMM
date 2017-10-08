//
// Created by Colten Sullivent on 10/3/17.
//

#ifndef IMM_SERIALIZEDTYPES_H
#define IMM_SERIALIZEDTYPES_H

#include <string>
#include <list>
#include <json.hpp>

using json = nlohmann::json;

namespace SerializedTypes {

  namespace SettingsFile {
    struct obj {
      int os;
      std::string smapiVersion;
      std::string installDir;
    };

    // region json

    inline void to_json(json& j, const obj& o) {
      j = json{{"os",o.os},{"smapiVersion",o.smapiVersion},{"installDir",o.installDir}};
    }

    inline void from_json(const json& j, obj& o) {
      o.os = j.at("os").get<int>();
      o.smapiVersion = j.at("smapiVersion").get<std::string>();
      o.installDir = j.at("installDir").get<std::string>();
      // TODO: change installDir to boost::filesystem::path
    }
    // endregion
  }

  namespace SmapiReleaseResponse {
    struct obj {
      std::string tag_name;
      std::string assets_url;
    };

    // region json

    inline void to_json(json& j, const obj& o) {
      j = json{{"tag_name",o.tag_name},{"assets_url",o.assets_url}};
    }

    inline void from_json(const json& j, obj& o) {
      o.tag_name = j.at("tag_name").get<std::string>();
      o.assets_url = j.at("assets_url").get<std::string>();
    }
    // endregion
  }

  namespace SmapiAssetElement {
    struct obj {
      std::string name;
      std::string browser_download_url;
    };

    // region json

    inline void to_json(json& j, const obj& o) {
      j = json{{"name",o.name},{"browser_download_url",o.browser_download_url}};
    }

    inline void from_json(const json& j, obj& o) {
      o.name = j.at("name").get<std::string>();
      o.browser_download_url = j.at("browser_download_url").get<std::string>();
    }
    // endregion
  }

  namespace Mod {
    struct obj {
      int type; // 0 is traditional, 1 is XNB
      std::string root;
      std::list<std::string> filesAdded;
      std::list<std::string> filesRemoved;
    };

    // region json

    inline void to_json(json& j, const obj& o) {
      j = json{{"type",o.type},{"root",o.root},{"filesAdded",o.filesAdded},{"filesRemoved",o.filesRemoved}};
    }

    inline void from_json(const json& j, obj& o) {
      o.type = j.at("type").get<int>();
      o.root = j.at("root").get<std::string>();
      o.filesAdded = j.at("filesAdded").get<std::list<std::string>>();
      o.filesRemoved = j.at("filesRemoved").get<std::list<std::string>>();
    }
    // endregion
  }
}

#endif //IMM_SERIALIZEDTYPES_H
