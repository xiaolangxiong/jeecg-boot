-- -author:chenrui---date:2025/1/16-----for:[QQYUN-10935]【jeecg】租户套餐管理优化---
UPDATE `sys_permission` SET `parent_id` = 'd7d6e2e4e2934f2c9385a623fd98c6f3', `name` = '租户初始套餐' WHERE `id` = '1668174661456171010';

-- -- author:chenrui---date:20250206--for: [QQYUN-11032]【jeecg】租户套餐管理增加初始化套餐包按钮 ---
INSERT INTO `sys_permission` (`id`, `parent_id`, `name`, `url`, `component`, `is_route`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_leaf`, `keep_alive`, `hidden`, `hide_tab`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`, `internal_or_external`) VALUES ('1887447660072292354', '1280350452934307841', '初始化套餐包', NULL, NULL, 0, NULL, NULL, 2, 'system:tenant:syncDefaultPack', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'jeecg', '2025-02-06 18:26:04', 'jeecg', '2025-02-06 18:26:53', 0, 0, '1', 0);